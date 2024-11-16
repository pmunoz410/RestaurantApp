package pm.project.restaurante_app.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pm.project.restaurante_app.entity.DocumentoAlmacenado;
import pm.project.restaurante_app.repository.IDocumentoAlmacenadoRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class DocumentoAlmacenadoService {
    private IDocumentoAlmacenadoRepository documentoAlmacenadoRepository;
    private FileStorageService storageService;

    public DocumentoAlmacenadoService(IDocumentoAlmacenadoRepository documentoAlmacenadoRepository, FileStorageService storageService) {
        this.documentoAlmacenadoRepository = documentoAlmacenadoRepository;
        this.storageService = storageService;
    }

    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, documentoAlmacenadoRepository.list());
    }


    public GenericResponse find(Long aLong) {
        return null;
    }


    public GenericResponse save(DocumentoAlmacenado obj) {
        String fileName = (documentoAlmacenadoRepository.findById(obj.getId())).orElse(new DocumentoAlmacenado()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA,documentoAlmacenadoRepository.save(obj));
    }

    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = documentoAlmacenadoRepository.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }

    public GenericResponse deleteById(Long id) {
        boolean deleted = false;
        Optional<DocumentoAlmacenado> documentoAlmacenado = documentoAlmacenadoRepository.findById(id);
        if (documentoAlmacenado.isPresent()) {
            deleted = storageService.deleteFile(documentoAlmacenado.get().getCompleteFileName());
            int deletedFromBD = documentoAlmacenadoRepository.deleteImageById(documentoAlmacenado.get().getId());
            if (deletedFromBD == 1 && deleted) {
                return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, deleted);
            } else {
                return new GenericResponse(TIPO_DATA, RPTA_WARNING, OPERACION_ERRONEA, deleted);
            }
        } else {
            return new GenericResponse(TIPO_DATA, RPTA_ERROR, "No se ha encontrado ningún documento almacenado con ese Id", deleted);
        }
    }
}