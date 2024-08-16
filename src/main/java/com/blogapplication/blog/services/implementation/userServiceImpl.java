package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.payloads.UserDTO;
import com.blogapplication.blog.repositories.UserRepo;
import com.blogapplication.blog.services.userService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements userService {

    Logger logger= LogManager.getLogger(userServiceImpl.class);



    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User newUser= UserDTOtoUser(userDTO);

        User savedUser=this.userRepo.save(newUser);
        logger.info("User saved in the Database with userID: "+userDTO.getId());
        UserDTO savedUserDTO=this.UsertoUserDTO(savedUser);

        return  savedUserDTO;


    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer id) {

        User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser=this.userRepo.save(user);
        logger.info("The user details got updated in the database with integer id as : "+ id);

        return this.UsertoUserDTO(updatedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
           List<User> allUsers=this.userRepo.findAll();

           List<UserDTO>alluserDTO=allUsers.stream().map(user->this.UsertoUserDTO(user)).collect(Collectors.toList());
           logger.info("All the users fetched successfully");
        return alluserDTO;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        logger.info("USer with the id "+userId+" fetched successfully ");
        return this.UsertoUserDTO(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        if(user!=null){
            userRepo.delete(user);
            logger.info("User with the userID "+userId+" deleted successfully");
        }else{
           // System.out.println("User can not be deleted");
        }
        return ;
    }

    public User UserDTOtoUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);


    }

    public UserDTO UsertoUserDTO(User user){
      return this.modelMapper.map(user,UserDTO.class);

    }
}
