package test.ubuntu.hp.oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
	// to configure the clients and their scopes
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
 
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) 
      throws Exception {
    	clients.inMemory()
        .withClient("secretClient")
        .secret("{noop}secret")
        .authorizedGrantTypes("refresh_token", "password", "client_credentials").scopes("read");
    }
 
    @Override
    public void configure(
      AuthorizationServerEndpointsConfigurer endpoints) 
      throws Exception {
  
        endpoints
          .tokenStore(tokenStore())
          .authenticationManager(authenticationManager);
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
