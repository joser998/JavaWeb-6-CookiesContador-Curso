package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Indicar al cliente el numero de veces que ha visitado nuestro sitio
        //variable contador
        int contador = 0;
        
        //revisamos si ya existe la cookie contador visitas
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c: cookies){
                if(c.getName().equals("contadorVisitas")){ //la cookie con la que estamos trabajando actualmente se llama: contadorVisitas
                    //obtenemos el contador de visitas actual
                        contador = Integer.parseInt(c.getValue()); //nos retorna un string por eso lo parseamos a int
                }
            }
        }
        
        //el valor que ya tenga acumulado el contador lo incrementamos en 1
        contador++;
        
        
        //agregamos la respuesta al navegador
        //convertimos de nuevo el integer en string
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
             
        //la cookie se almacenara en el cliente por una hora (3600 segs)
        c.setMaxAge(3600);
        response.addCookie(c);
        
        //Mandamos el mensaje al navegador
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter salida = response.getWriter();
        salida.print("Contador de visitas de cada usuario: "+contador);
        
    }
}