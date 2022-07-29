package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return (List<User>) userRepository.findAll();
    }
    public User saveUser(User user){
        String uniqueKey = UUID.randomUUID().toString();
        user.setId(uniqueKey);
        return userRepository.save(user);
    }
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public User findByLogin(User user){
        boolean check =
                findAll()
                    .stream()
                        .anyMatch(s -> s.getLogin().equals(user.getLogin()) && s.getPassword().equals(user.getPassword()));
        if(check) {
            return user;
        }
        else return null;
    }

    public User checkLogin(User user){
        boolean check =
                findAll()
                        .stream()
                        .anyMatch(s->s.getLogin().equals(user.getLogin()));
        if(check){
            return user;
        }else return null;
    }
}
