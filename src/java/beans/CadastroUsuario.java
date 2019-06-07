package beans;

import controller.UsuarioJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

@ManagedBean
@ViewScoped
public class CadastroUsuario {
    private Usuario usuario;
    private final EntityManagerFactory emf;


    public CadastroUsuario() {
        usuario = new Usuario();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }
    
    public void save(){
        UsuarioJpaController dao = new UsuarioJpaController(emf);
        dao.create(usuario);
    }
    public void update(){
        UsuarioJpaController dao = new UsuarioJpaController(emf);
        try {
            dao.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        UsuarioJpaController dao = new UsuarioJpaController(emf);
        try {
            dao.destroy(usuario.getId());
        } catch (Exception ex) {
            System.out.println("Erro:"+ex.getLocalizedMessage());
        }
    }
    public String count(){
        UsuarioJpaController dao = new UsuarioJpaController(emf);
        return ""+dao.getClienteCount();
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
       public List<Usuario> list(){
        UsuarioJpaController dao = new UsuarioJpaController(emf);
        return dao.findClienteEntities();   
    }
     
}
