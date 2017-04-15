//package lol.config;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * root
// * 2016年11月13日 上午9:50:11
// */
//public class WebSecurityConfigTest {
//	private static AuthenticationManager authenticationManager  = new SampleAuthenticationManager();
//	@Test
//	public void test() throws Exception {
////		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
////
////		 while(true) {
////		      System.out.println("Please enter your username:");
////		      String name = in.readLine();
////		      System.out.println("Please enter your password:");
////		      String password = in.readLine();
////		      try {
////		        Authentication request = new UsernamePasswordAuthenticationToken(name, password);
////		        Authentication result = authenticationManager.authenticate(request);
////		        SecurityContextHolder.getContext().setAuthentication(result);
////		        break;
////		      } catch(AuthenticationException e) {
////		        System.out.println("Authentication failed: " + e.getMessage());
////		      }
////		    }
////		    System.out.println("Successfully authenticated. Security context contains: " +
////		              SecurityContextHolder.getContext().getAuthentication());
//	}
//
//}
//class SampleAuthenticationManager implements AuthenticationManager{
//	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
//	static {
//		AUTHORITIES.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "ROLE_USER";
//			}
//		});
//	}
//	@Override
//	public Authentication authenticate(Authentication auth) throws AuthenticationException {
//		if(auth.getName().equals(auth.getCredentials())){
//			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),AUTHORITIES);
//		}
//		throw new BadCredentialsException("Bad credentials");
//	}
//
//}
