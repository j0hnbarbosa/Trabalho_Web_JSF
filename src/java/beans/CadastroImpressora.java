package beans;

import controller.ImpressoraJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Impressora;

@ManagedBean
@ViewScoped
public class CadastroImpressora {
    private Impressora impressora;
    private final EntityManagerFactory emf;


    public CadastroImpressora() {
        impressora = new Impressora();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }
    
    public void save(){
        ImpressoraJpaController dao = new ImpressoraJpaController(emf);
        dao.create(impressora);
    }
    public void update(){
        ImpressoraJpaController dao = new ImpressoraJpaController(emf);
        try {
            dao.edit(impressora);
        } catch (Exception ex) {
            Logger.getLogger(CadastroImpressora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        ImpressoraJpaController dao = new ImpressoraJpaController(emf);
        try {
            dao.destroy(impressora.getId());
        } catch (Exception ex) {
            System.out.println("Erro:"+ex.getLocalizedMessage());
        }
    }
    public String count(){
        ImpressoraJpaController dao = new ImpressoraJpaController(emf);
        return ""+dao.getClienteCount();
    }
    

    public Impressora getImpressora() {
        return impressora;
    }

    public void setImpressora(Impressora impressora) {
        this.impressora = impressora;
    }
       public List<Impressora> list(){
        ImpressoraJpaController dao = new ImpressoraJpaController(emf);
        return dao.findClienteEntities();   
    }
     
}
