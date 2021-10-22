package com.rounindiary.RouninDiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rounindiary.RouninDiary.entity.MUser;

public interface UserRepository extends JpaRepository<MUser, String>, JpaSpecificationExecutor<MUser>  {

	/** ログインユーザー検索 */
    @Query("select user"
            + " from MUser user"
            + " where id = :userId")
    public MUser findLoginUser(@Param("userId") String userId);

    /** ユーザー更新 */
    @Modifying
    @Query("update MUser"
            + " set"
            + "   password = :password"
            + "   , name = :name"
            + " where"
            + "   id = :userId")
    public Integer updateUser(@Param("userId") String userId,
            @Param("password") String password,
            @Param("name") String name);

}
