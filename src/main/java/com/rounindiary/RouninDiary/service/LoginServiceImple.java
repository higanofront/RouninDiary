package com.rounindiary.RouninDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rounindiary.RouninDiary.entity.MUser;
import com.rounindiary.RouninDiary.repository.UserRepository;

@Service
public class LoginServiceImple implements LoginService {

	@Autowired
	UserRepository userRepository;

	@Autowired
    private PasswordEncoder encoder;

	/** ユーザー登録 */
    @Transactional
    public void signup(MUser MUser) {

        // 存在チェック
        boolean exists = userRepository.existsById(MUser.getId());
        if(exists) {
            throw new DataAccessException("ユーザーが既に存在"){};
        }
        MUser.setRole("ROLE_GENERAL");

        // パスワード暗号化
        String rawPassword = MUser.getPassword();
        MUser.setPassword(encoder.encode(rawPassword));

        // insert
        userRepository.save(MUser);
    }

    public MUser findById(String id) {
		return userRepository.findById(id).get();
	}

}
