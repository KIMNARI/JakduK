package com.jakduk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jakduk.model.db.User;
import com.jakduk.model.simple.OAuthProfile;
import com.jakduk.model.simple.OAuthUserOnLogin;
import com.jakduk.model.simple.UserOnAuthentication;
import com.jakduk.model.simple.UserOnPasswordUpdate;
import com.jakduk.model.simple.UserProfile;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findById(String id);
	User findByUsername(String username);
	User findOneByUsername(String username);
	User findByEmail(String email);
	User findOneByEmail(String email);
	
	@Query(value="{'id' : ?0}", fields="{'username' : 1}")
	User writerFindById(String id);
	
	@Query(value="{'id' : ?0}")
	UserProfile userProfileFindById(String id);
	
	@Query(value="{'id' : ?0}")
	UserOnPasswordUpdate userOnPasswordUpdateFindById(String id);
	
	@Query(value="{'id' : {$ne : ?0}, 'username' : ?1}", fields="{'id' : 1, 'username' : 1}")
	UserProfile userFindByNEIdAndUsername(String id, String username);

	@Query(value="{'oauthUser.type' : ?0, 'oauthUser.oauthId' : ?1}")
	User userFindByOauthUser(String type, String oauthId);
	
	@Query(value="{'oauthUser.type' : ?0, 'oauthUser.oauthId' : ?1}")
	OAuthUserOnLogin findByOauthUser(String type, String oauthId);
	
	@Query(value="{'oauthUser.oauthId' : {$ne : ?0}, 'username' : ?1}", fields="{'id' : 1, 'username' : 1, 'oauthUser' : 1}")
	OAuthProfile userFindByNEOauthIdAndUsername(String oauthId, String username);
	
	@Query(value="{'oauthUser.type' : ?0, 'oauthUser.oauthId' : ?1}")
	OAuthProfile userfindByOauthUser(String type, String oauthId);
	
	@Query(value="{'email' : ?0}")
	UserOnAuthentication userFindByEmail(String email);
}
