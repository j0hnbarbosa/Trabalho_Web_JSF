package beans;

import controller.ControleUsoImpressoraJpaController;
import controller.exceptions.NonexistentEntityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.ControleUsoImpressora;

@ManagedBean
@ViewScoped
public class ControleUsoImpressoraBean {

    private ControleUsoImpressora casosuso;

    public ControleUsoImpressora getCasosuso() {
        return casosuso;
    }

    public void setCasosuso(ControleUsoImpressora casosuso) {
        this.casosuso = casosuso;
    }
    private final EntityManagerFactory emf;

    public ControleUsoImpressoraBean() {
        casosuso = new ControleUsoImpressora();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }

    public void save() {
        ControleUsoImpressoraJpaController dao = new ControleUsoImpressoraJpaController(emf);
        dao.create(casosuso);
    }

    public void update() {
        ControleUsoImpressoraJpaController dao = new ControleUsoImpressoraJpaController(emf);
        try {
            dao.edit(casosuso);
        } catch (Exception ex) {
            Logger.getLogger(ControleUsoImpressoraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        ControleUsoImpressoraJpaController dao = new ControleUsoImpressoraJpaController(emf);
        try {
            dao.destroy(casosuso.getId());
        } catch (Exception ex) {
            System.out.println("Erro:" + ex.getLocalizedMessage());
        }
    }

    public String count() {
        ControleUsoImpressoraJpaController dao = new ControleUsoImpressoraJpaController(emf);
        return "" + dao.getClienteCount();
    }

    public List<ControleUsoImpressora> list() {
        ControleUsoImpressoraJpaController dao = new ControleUsoImpressoraJpaController(emf);
        return dao.findImpressaoEntities();
    }

    public String getDataAtual() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
