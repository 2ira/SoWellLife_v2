package cn.mentalhealth.dao.impl;

import cn.mentalhealth.dao.ResourceDao;
import cn.mentalhealth.domain.Resource;
import cn.mentalhealth.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {
    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Resource> getAllResources() {
        List<Resource> resourceList = new ArrayList<>();
        String sql = "SELECT * FROM resource";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Resource resource = new Resource();
                resource.setRid(resultSet.getInt("Rid"));
                resource.setRname(resultSet.getString("Rname"));
                resource.setRTime(resultSet.getString("RTime"));
                resource.setRurl(resultSet.getString("Rurl"));
                resource.setRstore(resultSet.getInt("Rstore"));
                resource.setRtag(resultSet.getString("Rtag"));
                resource.setRabstract(resultSet.getString("Rabstract"));
                resourceList.add(resource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @Override
    public Resource getResourceById(int rid) {
        Resource resource = new Resource();
        String sql = "SELECT * FROM resource WHERE Rid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, rid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    resource.setRid(resultSet.getInt("Rid"));
                    resource.setRname(resultSet.getString("Rname"));
                    resource.setRTime(resultSet.getString("RTime"));
                    resource.setRurl(resultSet.getString("Rurl"));
                    resource.setRstore(resultSet.getInt("Rstore"));
                    resource.setRtag(resultSet.getString("Rtag"));
                    resource.setRabstract(resultSet.getString("Rabstract"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resource;
    }

    @Override
    public List<Resource> getResourcesByName(String rname) {
        List<Resource> resourceList = new ArrayList<>();
        String sql = "SELECT * FROM resource WHERE Rname LIKE '%' +? + '%'";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, rname);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Resource resource = new Resource();
                    resource.setRid(resultSet.getInt("Rid"));
                    resource.setRname(resultSet.getString("Rname"));
                    resource.setRTime(resultSet.getString("RTime"));
                    resource.setRurl(resultSet.getString("Rurl"));
                    resource.setRstore(resultSet.getInt("Rstore"));
                    resource.setRtag(resultSet.getString("Rtag"));
                    resource.setRabstract(resultSet.getString("Rabstract"));
                    resourceList.add(resource);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @Override
    public List<Resource> getResourcesByTag(String rtag) {
        List<Resource> resourceList = new ArrayList<>();
        String sql = "SELECT * FROM resource WHERE Rtag LIKE '%' +? + '%'";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, rtag);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Resource resource = new Resource();
                    resource.setRid(resultSet.getInt("Rid"));
                    resource.setRname(resultSet.getString("Rname"));
                    resource.setRTime(resultSet.getString("RTime"));
                    resource.setRurl(resultSet.getString("Rurl"));
                    resource.setRstore(resultSet.getInt("Rstore"));
                    resource.setRtag(resultSet.getString("Rtag"));
                    resource.setRabstract(resultSet.getString("Rabstract"));
                    resourceList.add(resource);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @Override
    public void insertResource(Resource resource) {
        String sql = "INSERT INTO resource (Rname, RTime, Rurl, Rstore, Rtag, Rabstract) VALUES (?,?,?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, resource.getRname());
            preparedStatement.setString(2, resource.getRTime());
            preparedStatement.setString(3, resource.getRurl());
            preparedStatement.setInt(4, resource.getRstore());
            preparedStatement.setString(5, resource.getRtag());
            preparedStatement.setString(6, resource.getRabstract());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResource(Resource resource, String condition) {
        String sql = "UPDATE resource SET " +
                "Rname =?, RTime =?, Rurl =?, Rstore =?, Rtag =?, Rabstract =? " +
                " WHERE " + condition;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, resource.getRname());
            preparedStatement.setString(2, resource.getRTime());
            preparedStatement.setString(3, resource.getRurl());
            preparedStatement.setInt(4, resource.getRstore());
            preparedStatement.setString(5, resource.getRtag());
            preparedStatement.setString(6, resource.getRabstract());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResource(String condition) {
        String sql = "DELETE FROM resource WHERE " + condition;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}