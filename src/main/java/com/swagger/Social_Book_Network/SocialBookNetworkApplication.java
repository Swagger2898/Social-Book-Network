package com.swagger.Social_Book_Network;

import com.swagger.Social_Book_Network.role.Role;
import com.swagger.Social_Book_Network.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class SocialBookNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialBookNetworkApplication.class, args);
//	    String rs="the fox is        an animal    ";
//		String s = reverseTheString(rs);
//		List<List<Integer>> rooms = Arrays.asList(
//				Arrays.asList(1,3),
//				Arrays.asList(1,4),
//				Arrays.asList(2,3,4,1),
//				Arrays.asList(),
//						Arrays.asList(4,3,2)
//		);
//		System.out.println(canVisitAllRooms(rooms));

	}



		public static boolean canVisitAllRooms(List<List<Integer>> rooms) {

			int n = rooms.size();
			int[] visited = new int[n];
			for(int i=0;i<n;i++){
				int j=0;
				while(j<rooms.get(i).size()){
         if(rooms.get(i).get(j)!=i) {
			 visited[rooms.get(i).get(j)] = 1;
			 j++;
		 }
		 else{
			 j++;
		 }
				}
			}
			for(int i=0;i<n;i++){
				if(visited[i]==0){
					return false;
				}
			}
			return true;
		}

//	public static String reverseTheString(String s){
//        StringBuilder sb = new StringBuilder();
//		String[] car =splitString(s," ");
//        for (int i=car.length-1;i>=0;i--) {
//            sb.append(car[i]);
//
//			if(i!=0){
//				sb.append(" ");
//			}
//        }
//		return sb.toString();
//	}

//	private static String[] splitString(String s, String s1) {
//		java.util.List<String> ls = new java.util.ArrayList<>();
//		 int j=0;
//		 StringBuilder sb = new StringBuilder();
//		for(int i=0;i<s.length();i++){
//			if(s.charAt(i)==s1.charAt(0) ){
//				if(sb.toString().length()>0){
//				ls.add(sb.toString());
//				sb = new StringBuilder();
//			}}
//			else{
//				sb.append(s.charAt(i));
//			}
//
//		}
//		ls.add(sb.toString());
//        return ls.toArray(new String[0]);
//	}


	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args ->{
			if(roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}
}
