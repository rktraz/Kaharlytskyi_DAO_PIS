package Model.Dao;

import Model.Entities.Dormitories;
import Model.Entities.Faculties;
import java.sql.*;
import java.util.ArrayList;

public class DaoFaculties implements IDao<Faculties> {
    final static private String SELECT = "select * from faculties where id = ?";
    final static private String SELECTALL = "select * from faculties";
    final static private String INSERT = "insert into faculties (name) values(?)";
    final static private String DELETE = "delete from faculties where id = ?";
    final static private String UPDATE = "update faculties set name = ? where id = ?";
    @Override
    public Faculties findById(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Faculties(
                    rs.getInt(1),
                    rs.getString(2)
            );
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Faculties> findAll() {
        ArrayList<Faculties> faculties = new ArrayList<>();
        try(Connection c = dataSource.getConnection()){
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(SELECTALL);
            while(rs.next()){
                faculties.add(new Faculties(
                        rs.getInt(1),
                        rs.getString(2))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return faculties;
    }

    @Override
    public void add(Faculties entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setString(1, entity.name());
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
    public void update(Faculties entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(UPDATE);
            ps.setString(1, entity.name());
            ps.setInt(2, entity.id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
