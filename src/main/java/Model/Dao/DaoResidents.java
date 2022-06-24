package Model.Dao;

import Model.Entities.Administrators;
import Model.Entities.Residents;

import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;

public class DaoResidents implements IDao<Residents> {
    final static private String SELECT = "select * from residents where id = ?";
    final static private String SELECTALL = "select * from residents";
    final static private String INSERT = "insert into residents (username, email, room, course, group, faculty_id, dormitory_id) values(?, ?, ?, ?, ?, ?, ?)";
    final static private String DELETE = "delete from residents where id = ?";
    final static private String UPDATE = "update residents set username = ?, email = ?, room = ?, course = ?, group = ?, faculty_id = ?, dormitory_id = ? where id = ?";
    @Override
    public Residents findById(Integer id) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Residents(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getInt(8));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Residents> findAll() {
        ArrayList<Residents> residents = new ArrayList<>();
        try(Connection c = dataSource.getConnection()){
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(SELECTALL);
            while(rs.next()){
                residents.add(new Residents(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return residents;
    }

    @Override
    public void add(Residents entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(INSERT);
                    ps.setString(1,entity.username());
                    ps.setString(2, entity.email());
                    ps.setString(3, entity.room());
                    ps.setInt(4, entity.course());
                    ps.setString(5, entity.group());
                    ps.setInt(6, entity.faculty_id());
                    ps.setInt(7, entity.dormitory_id());
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
    public void update(Residents entity) {
        try(Connection c = dataSource.getConnection()){
            PreparedStatement ps = c.prepareStatement(UPDATE);
            ps.setString(1,entity.username());
            ps.setString(2, entity.email());
            ps.setString(3, entity.room());
            ps.setInt(4, entity.course());
            ps.setString(5, entity.group());
            ps.setInt(6, entity.faculty_id());
            ps.setInt(7, entity.dormitory_id());
            ps.setInt(8, entity.id());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
