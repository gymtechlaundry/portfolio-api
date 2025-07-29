//package space.devincoopers.portfolio.filter;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//@Order(0) // Ensure it runs before ApiKeyFilter
//public class CorsFilter implements Filter {
//
//    private static final List<String> ALLOWED_ORIGINS = List.of(
//            "https://devincoopers.space",
//            "http://localhost:4200"
//    );
//
//    @Override
//    public void doFilter(ServletRequest req,
//                         ServletResponse res,
//                         FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        String origin = request.getHeader("Origin");
//
//        if (origin != null && ALLOWED_ORIGINS.contains(origin)) {
//            response.setHeader("Access-Control-Allow-Origin", origin);
//            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "x-api-key,Content-Type");
//            response.setHeader("Access-Control-Max-Age", "3600");
//        }
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//
//        chain.doFilter(req, res);
//    }
//}