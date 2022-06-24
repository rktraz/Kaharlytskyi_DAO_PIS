package Model;

import Model.Dao.*;

public class DaoFactory {
    public static DaoAdministrators createDaoAdministrators(){
        return new DaoAdministrators();
    }
    public static DaoDormitories createDaoDormitories(){
        return new DaoDormitories();
    }
    public static DaoFaculties createDaoFaculties(){
        return new DaoFaculties();
    }
    public static DaoPayments createDaoPayments(){
        return new DaoPayments();
    }
    public static DaoResidents createDaoResidents(){
        return new DaoResidents();
    }
}
