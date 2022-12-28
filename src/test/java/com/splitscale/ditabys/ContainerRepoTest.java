package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.Test;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.repositories.ContainerRepository;


public class ContainerRepoTest {
    @Test
    public void shouldAddContainerSuccessfully(){
        ContainerRequest containerRequest = new ContainerRequest("e7f60aa1-cc65-44bd-9150-2d9da00cef5b", "title");
        ContainerRepository repo = new ContainerRepositoryInteractor();


        try{
            Container container = repo.add(containerRequest);

            System.out.println(container.getUid());
            System.out.println(container.getName());
            System.out.println(container.getContainerID());

            assertNotNull(container);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldShowContainerIDSuccessfully(){
        ContainerRepositoryInteractor repo = new ContainerRepositoryInteractor();

        try{
            Container container = repo.getByContainerID(4);

            System.out.println(container.getName());
            System.out.println(container.getContainerID());  
              } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test 
    public void shouldShowUIDSuccessfully(){
        ContainerRepositoryInteractor repo = new ContainerRepositoryInteractor();

        try{
            Container container = repo.getByUid("e7f60aa1-cc65-44bd-9150-2d9da00cef5b");

            System.out.println(container.getName());
            System.out.println(container.getContainerID());  
              } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void shouldShowListofContainerTitle(){
        
    }
}
