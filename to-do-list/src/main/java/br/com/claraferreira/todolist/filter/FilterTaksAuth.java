package br.com.claraferreira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.claraferreira.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaksAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtém o caminho do servlet a partir da requisição
        var servletPath = request.getServletPath();

        // Verifica se o caminho começa com "/tasks/"
        if (servletPath.startsWith("/tasks/")) {

            // Obtém o cabeçalho "Authorization" da requisição
            var authorization = request.getHeader("Authorization");

            // Decodifica a parte do cabeçalho "Basic" para obter as credenciais
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            // Divide as credenciais em nome de usuário e senha
            String credentials[] = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Busca o usuário com o nome de usuário fornecido no repositório
            var user = userRepository.findByUsername(username);

            if (user == null) {
                // Se o usuário não existe, retorna um erro de autenticação (código 401)
                response.sendError(401);
            } else {
                // Verifica se a senha fornecida corresponde à senha armazenada no usuário
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    // Se a senha é verificada com sucesso, define um atributo "isUser" na requisição com o ID do usuário
                    request.setAttribute("isUser", user.getId());

                    // Encaminha a requisição para o próximo filtro ou servlet
                    filterChain.doFilter(request, response);
                } else {
                    // Se a senha não corresponde, retorna um erro de autenticação (código 401)
                    response.sendError(401);
                }
            }
        } else {
            // Se o caminho não começa com "/tasks/", encaminha a requisição para o próximo filtro ou servlet
            filterChain.doFilter(request, response);
        }
    }
}
