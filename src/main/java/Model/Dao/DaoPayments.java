package Model.Dao;

import Model.Entities.Dormitories;
import Model.Entities.Payments;

import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;

public class DaoPayments implements IDao<Payments> {
    final static private String SELECT = "select * from payments where id = ?";
    final static private String SELECTALL = "select * from payments";
    final static private String INSERT = "insert into payments (month_payed, resident_id) values(?, ?)";
    final static private String DELETE = "delete from payments where id = ?";
    final static private String UPDATE = "update payments set month_payed = ?, resident_id = ? where id = ?";
    @Override
    public Payments findById(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Payments(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Payments> findAll() {
        ArrayList<Payments> payments = new ArrayList<>();
        try(Connection c = dataSource.getConnection()){
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(SELECTALL);
            while(rs.next()){
                payments.add(new Payments(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void add(Payments entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setInt(1, entity.month_payed());
            ps.setInt(2, entity.resident_id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payments entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(UPDATE);
            ps.setInt(1, entity.month_payed());
            ps.setInt(2, entity.resident_id());
            ps.setInt(3, entity.id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
