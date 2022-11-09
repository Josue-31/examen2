package com.emergentes.controlador;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.GestorAlmacen;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Producto objAlmacen = new Producto();
        int id, pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null) ? request.getParameter("op"):"view";
        
        if(op.equals("nuevo")){
            HttpSession session = request.getSession();
            GestorAlmacen gestor = (GestorAlmacen)session.getAttribute("gestor");
            objAlmacen.setId(gestor.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miAlmacen", objAlmacen);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            GestorAlmacen gestor = (GestorAlmacen)session.getAttribute("gestor");
            pos = gestor.ubicarProducto(id);
            objAlmacen = gestor.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miAlmacen", objAlmacen);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            GestorAlmacen gestor = (GestorAlmacen)session.getAttribute("gestor");
            pos = gestor.ubicarProducto(id);
            gestor.eliminarProducto(pos);
            session.setAttribute("gestor", gestor);
            
            response.sendRedirect("index.jsp");
        }
        if(op.equals("view")){
            response.sendRedirect("index.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto objAlmacen = new Producto();
        int pos;
        String op = request.getParameter("op");
        if(op.equals("grabar")){
            objAlmacen.setId(Integer.parseInt(request.getParameter("id")));
            objAlmacen.setDescripcion(request.getParameter("descripcion"));
            objAlmacen.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            objAlmacen.setPrecio(Double.parseDouble(request.getParameter("precio")));
            objAlmacen.setCategoria(request.getParameter("categoria"));
            
            HttpSession session = request.getSession();
            GestorAlmacen gestor = (GestorAlmacen)session.getAttribute("gestor");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                gestor.insertarProducto(objAlmacen);
            }else{
                pos = gestor.ubicarProducto(objAlmacen.getId());
                gestor.modificarProducto(pos, objAlmacen);
            }
            session.setAttribute("gestor", gestor);
            response.sendRedirect("index.jsp");
        }
    }
}
