package com.user.demo.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.user.demo.entity.UserEntity;

@Repository
public class UserRepository {

	private static final String dataDir = "C:/GitLocation/Git/folderforgit/UserManagerApplication/data/";

	public UserEntity save(UserEntity user) throws IOException {
        File file = new File(dataDir+user.getId()+".ser");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(user);
        }
        return user;
    }

    public UserEntity findById(Long id) throws IOException, ClassNotFoundException {
        File file = new File(dataDir+"//"+id+".ser");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (UserEntity) in.readObject();
        }
    }
    public List<UserEntity> findAll() throws IOException, ClassNotFoundException {
        List<UserEntity> users = new ArrayList<>();
        File dir = new File(dataDir);
        for (File file : dir.listFiles()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                users.add((UserEntity) in.readObject());
            }
        }
        return users;
    }
	public boolean deleteById(Long id) {
		File file = new File(dataDir + id + ".ser");
		return file.delete();
	}

	public UserEntity updateUser(UserEntity userEntity, Long id) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(dataDir+id+".ser");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			userEntity.setId(id);
			if (userEntity.getId().equals(id)) {
            	userEntity.setUsername(userEntity.getUsername());
            	userEntity.setPassword(userEntity.getPassword());
            	try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
					objectOutputStream.writeObject(userEntity);
					userEntity=(UserEntity) in.readObject();
				}
            }
        }
		return userEntity;
	}
}
