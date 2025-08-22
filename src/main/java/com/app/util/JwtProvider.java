package com.app.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.app.dto.study.Member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

//JWT 관련 처리 클래스
public class JwtProvider {

	// 비밀키 설정
	private static final String SECRET_KEY = "thisissecretkeyforjwtreactconnectwithspringserverthisissecretkeyforjwtreactconnectwithspringserver";

	// Access Token & Refresh Token 만료시간 설정
	private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 30; // 30분
	private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7일

	//시크릿키 생성 (비밀키 변환으로 키 생성)
	private static SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	//시크릿키 생성 (비밀키 -> Base64 인코딩 -> 키 생성)

	/*
	 * AccessToken 생성.
	 * 현재 로그인 처리할 사용자의 아이디를 기준으로 토큰 생성
	 */
	public static String createAccessToken(String userId) {
		Date now = new Date(System.currentTimeMillis());

		// SecretKey 는 binary 데이터
		// 문자열로 변환시 Base64 Encoding 이 필요함

		/* 토큰이 보관할 회원ID */
		// 비공개 클레임
		Claims claims = Jwts.claims().add("userId", userId).build();
		
		return Jwts.builder()
					.header()
					.add("typ", "JWT")
					.and()
					.subject("accessToken")
					.issuedAt(now)
					.issuer("spring server")
					.expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
					.claims(claims)
					.signWith(getSigningKey(), Jwts.SIG.HS256)
					.compact();
	}
	
	public static String createAccessToken(Member member) {
		return createAccessToken(member.getId());
	}

	/* 토큰 해석 메소드 - 토큰을 해석하여 저장된 userId 를 획득한다 */
	public static String getUserIdFromToken(String token) { // throws CustomException {

		String userId = null;
		try {
			userId = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload()
					.get("userId", String.class);
		} catch (ExpiredJwtException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("토큰 해석 userId value : " + userId);

		return userId;
	}

	/* 유효성 확인(해독된 jwt) */
	public static boolean isVaildToken(String token) {
		
		//return 은 유효여부 (true/false)를 반환하거나
		//상태별 상태코드로 구분해서 반환하거나 정해서 처리

		// 인증된 (Authenticated)
		// 만료된 (Expired)
		// 유효하지않은 (Invalid)

		try {
			Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
			return true; // 유효하다면 true 반환
		} catch (MalformedJwtException e) {
			System.out.println("유효하지 않은 토큰: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("만료된 토큰: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("지원하지 않는 토큰: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("검증 실패 토큰: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Refresh Token 생성
	 */
	public static String createRefreshToken() {
		return Jwts.builder()
				.issuedAt(new Date())
				.issuer("spring server")
				.expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
				.signWith(getSigningKey(), Jwts.SIG.HS256)
				.compact();
	}

	/**
	 * Refresh Token을 이용한 새로운 Access Token 발급
	 */
	public static String refreshAccessToken(String refreshToken, String userId) {
		if (isVaildToken(refreshToken)) {
			return createAccessToken(userId);
		}
		//throw new RuntimeException("유효하지 않은 Refresh Token");
		//exception 처리 하거나
		//return null 혹은 별도 값을 리턴하여 처리
		return null;
	}
	
	public static String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
