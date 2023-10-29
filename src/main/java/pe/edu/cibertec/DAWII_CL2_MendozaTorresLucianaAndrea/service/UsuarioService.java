package pe.edu.cibertec.DAWII_CL2_MendozaTorresLucianaAndrea.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWII_CL2_MendozaTorresLucianaAndrea.model.bd.Rol;
import pe.edu.cibertec.DAWII_CL2_MendozaTorresLucianaAndrea.model.bd.Usuario;
import pe.edu.cibertec.DAWII_CL2_MendozaTorresLucianaAndrea.repository.RolRepository;
import pe.edu.cibertec.DAWII_CL2_MendozaTorresLucianaAndrea.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=
            new BCryptPasswordEncoder();

    public Usuario findUserByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findUserByUserName(String username){
        return usuarioRepository.findByNomusuario(username);
    }

    public Usuario saveUser(Usuario usuario){
        usuario.setPassword(bCryptPasswordEncoder.encode(
                usuario.getPassword()));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }
}
