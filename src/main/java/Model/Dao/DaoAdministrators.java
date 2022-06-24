package Model.Dao;

import Model.Entities.Administrators;

import java.sql.*;
import java.util.ArrayList;

public class DaoAdministrators implements IDao<Administrators> {
    final static private String SELECT = "select * from administrators where id = ?";
    final static private String SELECTALL = "select * from administrators";
    final static private String INSERT = "insert into administrators (username, email, password) values(?, ?, ?)";
    final static private String DELETE = "delete from administrators where id = ?";
    final static private String UPDATE = "update administrators set username = ?, email = ?, password = ? where id = ?";
    @Override
    public Administrators findById(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Administrators(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Administrators> findAll() {
        ArrayList<Administrators> administrators = new ArrayList<>();
        try(Connection c = dataSource.getConnection()){
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(SELECTALL);
            while(rs.next()){
                administrators.add(new Administrators(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return administrators;
    }

    @Override
    public void add(Administrators entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setString(1, entity.username());
            ps.setString(2, entity.email());
            ps.setString(3, entity.password());
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
    public void update(Administrators entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(UPDATE);
            ps.setString(1, entity.username());
            ps.setString(2, entity.email());
            ps.setString(3, entity.password());
            ps.setInt(4, entity.id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
