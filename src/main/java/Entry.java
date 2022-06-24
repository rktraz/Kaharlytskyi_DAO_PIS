import Model.Dao.DaoAdministrators;
import Model.Dao.DaoFaculties;
import Model.DaoFactory;
import Model.Entities.Administrators;
import Model.Entities.Faculties;

public class Entry {
    public static void main(String[] args) {
        DaoFaculties daoFaculties = DaoFactory.createDaoFaculties();
//        daoFaculties.add(new Faculties(null, "BABABA"));
        daoFaculties.delete(3);
//        DaoAdministrators daoAdministrators = DaoFactory.createDaoAdministrators();
//        daoAdministrators.add(new Administrators(null, "12253","152334","2454442"));
        System.out.println(daoFaculties.findAll());
//        daoAdministrators.update(new Administrators(5, "15253new","124534new","2455442new"));
//        System.out.println(daoAdministrators.findById(4));
//        daoAdministrators.delete(5);
//        System.out.println(daoAdministrators.findAll());
    }
}
