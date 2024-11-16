package pm.project.restaurante_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import pm.project.restaurante_app.entity.Usuario;
import pm.project.restaurante_app.service.UsuarioService;
import pm.project.restaurante_app.utils.GenericResponse;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return this.usuarioService.login(email, password);
    }

    @PostMapping
    public GenericResponse save(@RequestBody Usuario user){
        return this.usuarioService.guardarUsuario(user);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @RequestBody Usuario user){
        return this.usuarioService.guardarUsuario(user);
    }
}