package pm.project.restaurante_app.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pm.project.restaurante_app.entity.DocumentoAlmacenado;

import java.util.Optional;

public interface IDocumentoAlmacenadoRepository extends JpaRepository<DocumentoAlmacenado, Long> {
    @Query("SELECT da FROM DocumentoAlmacenado da WHERE da.estado = 'A' AND da.eliminado = false")
    Iterable<DocumentoAlmacenado> list();

    @Query("SELECT da FROM DocumentoAlmacenado da WHERE da.fileName = :fileName AND da.estado = 'A' AND da.eliminado = false")
    Optional<DocumentoAlmacenado> findByFileName(String fileName);

    @Transactional
    @Modifying
    @Query("DELETE FROM DocumentoAlmacenado da WHERE da.id = :id")
    int deleteImageById(Long id);
}