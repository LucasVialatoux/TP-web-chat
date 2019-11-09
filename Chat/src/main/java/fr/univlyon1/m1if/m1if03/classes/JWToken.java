package fr.univlyon1.m1if.m1if03.classes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Calendar;
import java.util.Date;   

public class JWToken{ 

    public static String createJWT(String subject) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 3);
        Date later = calendar.getTime(); 
        String token="";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                .withIssuer("API/Chat")
                .withSubject(subject)
                .withExpiresAt(later)
                .sign(algorithm);
        } catch (JWTCreationException exception){
        }
        return token;
    }
    
    public static String decodeJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("API/Chat")
            .build();
        try{
            return verifier.verify(token).getSubject();
        }catch(JWTVerificationException exception){
            return null;
        }
    }
    
}