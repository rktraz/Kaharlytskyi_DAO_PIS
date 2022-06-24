package Model.Dao;

import Model.Entities.Administrators;
import Model.Entities.Dormitories;

import java.util.ArrayList;

import java.sql.*;
import java.util.ArrayList;

public class DaoDormitories implements IDao<Dormitories> {
    final static private String SELECT = "select * from dormitories where id = ?";
    final static private String SELECTALL = "select * from dormitories";
    final static private String INSERT = "insert into dormitories (number, address, administrator_id) values(?, ?, ?)";
    final static private String DELETE = "delete from dormitories where id = ?";
    final static private String UPDATE = "update dormitories set number = ?, address = ?, administrator_id = ? where id = ?";
    @Override
    public Dormitories findById(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Dormitories(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Dormitories> findAll() {
        ArrayList<Dormitories> dormitories = new ArrayList<>();
        try(Connection c = dataSource.getConnection()){
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(SELECTALL);
            while(rs.next()){
                dormitories.add(new Dormitories(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return dormitories;
    }

    @Override
    public void add(Dormitories entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setInt(1, entity.number());
            ps.setString(2, entity.address());
            ps.setInt(3, entity.administrator_id());
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
    public void update(Dormitories entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(UPDATE);
            ps.setInt(1, entity.number());
            ps.setString(2, entity.address());
            ps.setInt(3, entity.administrator_id());
            ps.setInt(4, entity.id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
