package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//Показывает что это компонент спринга
//Принимает запросы из вне и дёргает репозиторный метод
public class UserService {
    private UserRepository userRepository;

    @Autowired//Автосвязывание(внедрение)
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteById(int id){
        userRepository.deleteById(id);
    }
}
