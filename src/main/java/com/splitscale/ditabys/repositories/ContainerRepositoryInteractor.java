package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.ditabys.driver.StoreDbDriver;
import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.repositories.ContainerRepository;

public class ContainerRepositoryInteractor implements ContainerRepository {
    DatabaseDriver db;

    public ContainerRepositoryInteractor() {
        this.db = new StoreDbDriver();
    }

    @Override
    public Container add(ContainerRequest containerRequest) throws IOException {
        final String query = "INSERT INTO container (container_id, container_title, user_id) VALUES (null,?,UUID_TO_BIN(?))";

        final String containerTitle = containerRequest.getName();
        final String userId = containerRequest.getUid();

        Container container = new Container();
        container.setName(containerTitle);
        container.setUid(userId);

        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, containerTitle);
            pstmt.setString(2, userId);


            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
              ResultSet rs = pstmt.getGeneratedKeys();
              if (rs.next()) {
                long id = rs.getLong(1);
                container.setContainerID(id);
              }
            }

            conn.close();
            return container;

        } catch (SQLException e){
            throw new IOException("Could not add a new container to database" + e.getMessage());
        }
    }

    @Override
    public boolean delete(long arg0) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Container getByContainerID(long container_id) throws IOException {
        final String query = "Select * From container WHERE container_id = ?";
        Container container = new Container();

        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, container_id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                container.setContainerID(rs.getLong("container_id"));
                container.setName(rs.getString("container_title"));
            }
            conn.close();
            return container;
        } catch (SQLException e){
            throw new IOException("Could not find this container ID");
        }
    }

    @Override
    public Container getByUid(String arg0) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Container> getListByName(String arg0) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Container> getListByUid(String arg0) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container update(Container arg0) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }


    
}
