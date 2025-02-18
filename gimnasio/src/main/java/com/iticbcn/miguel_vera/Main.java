package com.iticbcn.miguel_vera;

import org.hibernate.SessionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import com.iticbcn.miguel_vera.DAO.ClaseDAO;
import com.iticbcn.miguel_vera.DAO.GimnasioDAO;
import com.iticbcn.miguel_vera.DAO.PersonaDAO;
import com.iticbcn.miguel_vera.DAO.SocioDAO;
import com.iticbcn.miguel_vera.model.Clase;
import com.iticbcn.miguel_vera.model.Gimnasio;
import com.iticbcn.miguel_vera.model.Persona;
import com.iticbcn.miguel_vera.model.Socio;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main( String[] args ) throws IOException {  
        try (SessionFactory sesion = HibernateUtil.getSessionFactory() ){ 

            Terminal terminal = TerminalBuilder.builder().system(true).build();

            String message = "";
            message = "==================";
            printScreen(terminal, message);
    
            message = "CONSULTA BD Gym";
            printScreen(terminal, message);
    
            message = "==================";
            printScreen(terminal, message);
    
            message = "OPCIONES";
            printScreen(terminal, message);
    
            message = "1. CREAR UN NUEVO REGISTRO";
            printScreen(terminal, message);
        
            message = "2. BUSCAR REGISTRO";
            printScreen(terminal, message);
    
            message = "3. MODIFICAR REGISTRO";
            printScreen(terminal, message);
            
            message = "4. BORRAR REGISTRO";
            printScreen(terminal, message);

            message = "5. MOSTRAR TODO";
            printScreen(terminal, message);
    
            message = "6. SALIR";
            printScreen(terminal, message);
    
            message = "Introduce la opcion >> ";
            for (char c : message.toCharArray()) {
                terminal.writer().print(c);
                terminal.flush();
                Thread.sleep(10);
            }
    
            int opcio = Integer.parseInt(br.readLine());

            switch(opcio) {
                case 1:
                    MenuInsert(sesion, terminal);
                    break;
                case 2:
                    MenuFind(sesion, terminal);
                    break;
                case 3:
                    MenuUpdate(sesion, terminal);
                    break;
                case 4:
                    MenuDelete(sesion, terminal);
                    break;
                case 5:
                    MenuFindAll(sesion, terminal);
                    break;
                case 6:
                    System.out.println("Adiós");
                    break;
                default:
                    System.out.print("Opción no válida");
            }
            
        }  catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }

    public static void Menu(Terminal terminal) throws InterruptedException, IOException {

        String message = "";
                
        message = "SELECCIONE LA TABLA";
        printScreen(terminal, message);
    
        message = "1. PERSONA";
        printScreen(terminal, message);
        
        message = "2. SOCIO";
        printScreen(terminal, message);
    
        message = "3. GIMNASIO";
        printScreen(terminal, message);
            
        message = "4. CLASE";
        printScreen(terminal, message);

        message = "5. SALIR";
        printScreen(terminal, message);
    
        message = "Introduce la opcion >> ";
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }    
    }

    public static void MenuInsert(SessionFactory sesion, Terminal terminal) throws InterruptedException, IOException, Exception {
        
        Menu(terminal);
        int opcion = Integer.parseInt(br.readLine());

        switch(opcion) {
            case 1:
                insertPersona(sesion);
                break;
            case 2:
                insertSocio(sesion);
                break;
            case 3:
                insertGimnasio(sesion);
                break;
            case 4:
                insertClase(sesion);
                break;
            case 5:
                System.out.println("Adiós");
                break;
            default:
                System.out.print("Opción no válida");
                Menu(terminal);
        }
    }

    public static void MenuFind(SessionFactory sesion, Terminal terminal) throws InterruptedException, IOException, Exception{
        Menu(terminal);
        int opcion = Integer.parseInt(br.readLine());

        switch(opcion) {
            case 1:
                System.out.print("Introduzca el ID de la persona a consultar >> ");
                int idPersona = Integer.parseInt(br.readLine());
                Persona persona = findPersona(sesion, idPersona);
                System.out.println( persona.toString());
                break;
            case 2:
                System.out.print("Introduzca el ID del socio a consultar >> ");
                int idSocio = Integer.parseInt(br.readLine());
                Socio socio = findSocio(sesion, idSocio);
                System.out.println( socio.toString());
                break;
            case 3:
                System.out.print("Introduzca el ID del gimnasio a consultar >> ");
                int idGimnasio = Integer.parseInt(br.readLine());
                Gimnasio gimnasio = findGimnasio(sesion, idGimnasio);
                System.out.println( gimnasio.toString());
                break;
            case 4:
                System.out.print("Introduzca el ID de la clase a consultar >> ");
                int idClase = Integer.parseInt(br.readLine());
                Clase clase = findClase(sesion, idClase);
                System.out.println(clase.toString());
                break;
            case 5:
                System.out.println("Adiós");
                break;
            default:
                System.out.print("Opción no válida");
                Menu(terminal);
        }
    }

    public static void MenuUpdate(SessionFactory sesion, Terminal terminal) throws InterruptedException, IOException, Exception {
        Menu(terminal);
        int opcion = Integer.parseInt(br.readLine());

        switch(opcion) {
            case 1:
                updatePersona(sesion);
                break;
            case 2:
                updateSocio(sesion);
                break;
            case 3:
                updateGimnasio(sesion);
                break;
            case 4:
                updateClase(sesion);
                break;
            case 5:
                System.out.println("Adiós");
                break;
            default:
                System.out.print("Opción no válida");
                Menu(terminal);
        }
    }

    public static void MenuDelete(SessionFactory sesion, Terminal terminal) throws InterruptedException, IOException, Exception{
        Menu(terminal);
        int opcion = Integer.parseInt(br.readLine());

        switch(opcion) {
            case 1:
                deletePersona(sesion);
                break;
            case 2:
                deleteSocio(sesion);
                break;
            case 3:
                deleteGimnasio(sesion);
                break;
            case 4:
                deleteClase(sesion);
                break;                
            case 5:
                System.out.println("Adiós");
                break;
            default:
                System.out.print("Opción no válida");
                Menu(terminal);
        }
    }

    public static void MenuFindAll(SessionFactory sesion, Terminal terminal) throws InterruptedException, IOException, Exception {
        Menu(terminal);
        int opcion = Integer.parseInt(br.readLine());

        switch(opcion) {
            case 1:
                findAllPersona(sesion);
                break;
            case 2:
                findAllSocio(sesion);
                break;
            case 3:
                getAll(sesion);
                break;
            case 4:
                findAllClase(sesion);
                break;
            case 5:
                System.out.println("Adiós");
                break;
            default:
                System.out.print("Opción no válida");
                Menu(terminal);
        }
    }

    // CRUD entidad SOCIO

    public static void insertPersona(SessionFactory sesion) throws IOException{
        System.out.print("Introduzca el DNI de la persona >> ");
        String dni = br.readLine();
        System.out.print("Introduzca la nombre de la persona >> ");
        String nombre = br.readLine();
        System.out.print("Introduzca el teléfono de la persona >> ");
        int telefono = Integer.parseInt(br.readLine());
        Persona persona = new Persona (dni, nombre, telefono);
        PersonaDAO.create(sesion, persona);
    }

    public static Persona findPersona(SessionFactory sesion, int id) {
        PersonaDAO pDAO = new PersonaDAO(sesion);
        Persona persona = pDAO.find(id);
        return persona;
    }

    public static void updatePersona(SessionFactory sesion) throws IOException{
        System.out.println("\nQué Campo desea actualizar?");
        System.out.println("1. DNI  2. Nombre    3. Teléfono");
        
        System.out.print(">> ");
        int opcion = Integer.parseInt(br.readLine());

        System.out.print("\nIntroduzca el ID de la persona >> ");
        int id = Integer.parseInt(br.readLine());

        Persona persona;
        while (true) {        
            persona = findPersona(sesion, id);
            if (persona == null) {
                System.out.println("\nPersona no encontrada.");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
            } else {
                break;
            }
        }
        switch (opcion) {
            case 1:
                System.out.print("Introduzca el nuevo DNI >> ");
                String dniPersona = br.readLine();
                persona.setDNI(dniPersona);
                break;
            case 2:
                System.out.print("Introduzca el nuevo nombre >> ");
                String nombre = br.readLine();
                persona.setNombre(nombre);
                break;
            case 3:
                System.out.print("Introduzca el nuevo teléfono >> ");
                int telefono = Integer.parseInt(br.readLine());
                persona.setTelefono(telefono);
                break;
            default:
                System.out.println("Opción incorrecta");
                return;
        }
        PersonaDAO.update(persona);
    }

    public static void deletePersona(SessionFactory sesion) throws IOException{
        System.out.print("Indique el ID de la persona a eliminar >> ");
        int id = Integer.parseInt(br.readLine());
        Persona persona = findPersona(sesion,id);
        while(true) {
            if (persona != null) {
                ClaseDAO.delete(id);
                System.out.println("Persona borrada con éxito");
                break;
            } else {
                System.out.println("\nNo se encontro la persona");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
                persona = findPersona(sesion,id);
            }
        }
    }

    public static void findAllPersona(SessionFactory sesion) throws IOException {
        PersonaDAO pDAO = new PersonaDAO(sesion);
        List<Persona> personaList = pDAO.findAll();
        for (Persona persona : personaList) {
            System.out.println(persona.toString());
        }
    }

    public static void findGroupByPersona(SessionFactory sesion) throws IOException {
        PersonaDAO pDAO = new PersonaDAO(sesion);
        List<Object[]> personaList = pDAO.findGroupBy();
        for (Object[] persona : personaList) {
            System.out.println("Nombres: " + persona[0] + ", Cantidad de personas: " + persona[1]);
        }
    }

    // CRUD entidad SOCIO

    public static void insertSocio(SessionFactory sesion) throws Exception{
        System.out.print("Introduzca el ID de la persona >> ");
        int idPersona = Integer.parseInt(br.readLine());
        Persona persona = findPersona(sesion, idPersona);
        if (persona != null) {
            System.out.print("Introduzca el ID del gimnasio >> ");
            int idGimnasio = Integer.parseInt(br.readLine());
            Gimnasio gimnasio = findGimnasio(sesion, idGimnasio);
            if (gimnasio != null) {
                System.out.print("Introduzca la edad del socio >> ");
                int edad = Integer.parseInt(br.readLine());
                System.out.print("Introduzca el email del socio >> ");
                String email = br.readLine();
                System.out.print("Introduzca el fecha de inscripción del socio >> ");
                String fechaInscripcion = br.readLine();
                System.out.print("Introduzca la cuota a pagar del socio >> ");
                float cuota = Float.parseFloat(br.readLine());
                Socio socio = new Socio (persona, edad, email, fechaInscripcion, cuota, gimnasio );
                SocioDAO.create(sesion, socio);
            } else {
                System.out.println("ID del gimnasio incorrecto, vuelva a intentar");
                insertSocio(sesion);
            }
        } else {
            System.out.println("ID de la persona incorrecto, vuelva a intentar");
            insertSocio(sesion);
        }
    }

    public static Socio findSocio(SessionFactory sesion, int id) throws IOException{
        SocioDAO sDAO = new SocioDAO(sesion);
        Socio socio = sDAO.find(id);
        return socio;
    }

    public static void updateSocio(SessionFactory sesion) throws IOException{
        System.out.println("\nQué Campo desea actualizar?");
        System.out.println("1. edad  2. email    3. fecha de inscripción    4. cuota");
        
        System.out.print(">> ");
        int opcion = Integer.parseInt(br.readLine());

        System.out.print("\nIntroduzca el ID del socio >> ");
        int id = Integer.parseInt(br.readLine());

        Socio socio;
        while (true) {        
            socio = findSocio(sesion, id);
            if (socio == null) {
                System.out.println("\nSocio no encontrado.");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
            } else {
                break;
            }
        }
        switch (opcion) {
            case 1:
                System.out.print("Introduzca la nueva edad >> ");
                int edad = Integer.parseInt(br.readLine());
                socio.setEdad(edad);
                break;
            case 2:
                System.out.print("Introduzca el nuevo email >> ");
                String email = br.readLine();
                socio.setEmail(email);
                break;
            case 3:
                System.out.print("Introduzca la nueva fecha >> ");
                String fecha = br.readLine();
                socio.setFechaInscripcion(fecha);
                break;
            case 4:
                System.out.print("Introduzca la nueva cuota >> ");
                Float cuota = Float.parseFloat(br.readLine());
                socio.setCuota(cuota);
                break;
            default:
                System.out.println("Opción incorrecta");
                return;
        }
        SocioDAO.update(socio);
    }

    public static void deleteSocio(SessionFactory sesion) throws IOException {
        System.out.print("Indique el ID del socio a eliminar >> ");
        int id = Integer.parseInt(br.readLine());
        Socio socio = findSocio(sesion,id);
        while(true) {
            if (socio != null) {
                ClaseDAO.delete(id);
                System.out.println("Socio borrado con éxito");
                break;
            } else {
                System.out.println("\nNo se encontro el socio");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
                socio = findSocio(sesion,id);
            }
        }
    }

    public static void findAllSocio(SessionFactory sesion) throws IOException {
        SocioDAO sDAO = new SocioDAO(sesion);
        List<Socio> socioList = sDAO.findAll();
        for (Socio socio : socioList) {
            System.out.println(socio.toString());
        }
    }

    public static void findGroupBySocio(SessionFactory sesion) throws IOException {
        SocioDAO sDAO = new SocioDAO(sesion);
        List<Object[]> socioList = sDAO.findGroupBy();
        for (Object[] socio : socioList) {
            System.out.println("Fecha de Inscripción: " + socio[0] + ", Cantidad de socios: " + socio[1]);
        }
    }

    // CRUD entidad CLASE

    public static void insertGimnasio(SessionFactory sesion) throws IOException, Exception{
        System.out.print("Introduzca el nombre del gimnasio >> ");
        String nombre = br.readLine();
        System.out.print("Introduzca el aforo del gimnasio >> ");
        int aforo = Integer.parseInt(br.readLine());
        System.out.print("Introduzca la ubicación del gimnasio >> ");
        String ubicacion = br.readLine();
        Gimnasio gimnasio = new Gimnasio (nombre, aforo, ubicacion);
        GimnasioDAO gDAO = new GimnasioDAO(sesion);
        gDAO.save(gimnasio);
    }

    public static Gimnasio findGimnasio(SessionFactory sesion, int id) throws Exception{
        GimnasioDAO gDAO = new GimnasioDAO(sesion);
        Gimnasio gimnasio = gDAO.get(id);
        return gimnasio;
    }

    public static void updateGimnasio(SessionFactory sesion) throws IOException, Exception{
        System.out.println("\nQué Campo desea actualizar?");
        System.out.println("1. nombre  2. aforo    3. ubicación");
        
        System.out.print(">> ");
        int opcion = Integer.parseInt(br.readLine());

        System.out.print("\nIntroduzca el ID del gimnasio >> ");
        int id = Integer.parseInt(br.readLine());
        GimnasioDAO gDAO = new GimnasioDAO(sesion);
        Gimnasio gimnasio;
        while (true) {        
            gimnasio = findGimnasio(sesion, id);
            if (gimnasio == null) {
                System.out.println("\nGimnasio no encontrado.");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
            } else {
                break;
            }
        }
        switch (opcion) {
            case 1:
                System.out.print("Introduzca el nuevo nombre >> ");
                String nombre = br.readLine();
                gimnasio.setNombre(nombre);
                break;
            case 2:
                System.out.print("Introduzca el nuevo aforo >> ");
                int aforo = Integer.parseInt(br.readLine());
                gimnasio.setAforo(aforo);
                break;
            case 3:
                System.out.print("Introduzca la nueva ubicación >> ");
                String ubicacion = br.readLine();
                gimnasio.setUbicacion(ubicacion);
                break;
            default:
                System.out.println("Opción incorrecta");
                return;
        }
        gDAO.update(gimnasio);
    }

    public static void deleteGimnasio(SessionFactory sesion) throws Exception {
        System.out.print("Indique el ID del gimnasio a eliminar >> ");
        int id = Integer.parseInt(br.readLine());
        Gimnasio gimnasio = findGimnasio(sesion,id);
        while(true) {
            if (gimnasio != null) {
                ClaseDAO.delete(id);
                System.out.println("Gimnasio borrado con éxito");
                break;
            } else {
                System.out.println("\nNo se encontro el Gimnasio");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
                gimnasio = findGimnasio(sesion,id);
            }
        }
    }

    public static void getAll(SessionFactory sesion) throws Exception {
        GimnasioDAO gDAO = new GimnasioDAO(sesion);
        List<Gimnasio> gimnasioList = gDAO.getAll();
        for (Gimnasio gimnasio : gimnasioList) {
            System.out.println(gimnasio.toString());
        }
    }

        // CRUD entidad CLASE

    public static void insertClase(SessionFactory sesion) throws IOException{
        System.out.print("Introduzca el nombre de la clase >> ");
        String nombre = br.readLine();
        System.out.print("Introduzca el horario de la clase >> ");
        String horario = br.readLine();
        System.out.print("Introduzca el aforo de la clase >> ");
        int aforo = Integer.parseInt(br.readLine());
        Clase clase = new Clase (nombre, horario, aforo);
        ClaseDAO.create(sesion,clase);
    }

    public static Clase findClase (SessionFactory sesion, int id) {
        ClaseDAO cDAO = new ClaseDAO(sesion);
        Clase clase = cDAO.find(id);
        return clase;
    }

    public static void updateClase(SessionFactory sesion) throws IOException{
        System.out.println("\nQué Campo desea actualizar?");
        System.out.println("1. nombre  2. horario    3. aforo");
        
        System.out.print(">> ");
        int opcion = Integer.parseInt(br.readLine());

        System.out.print("\nIntroduzca el ID de la clase >> ");
        int id = Integer.parseInt(br.readLine());

        Clase clase;
        while (true) {        
            clase = findClase(sesion, id);
            if (clase == null) {
                System.out.println("\nClase no encontrada.");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
            } else {
                System.out.print("Los datos son: ");
                clase.toString();
                break;
            }
        }
        switch (opcion) {
            case 1:
                System.out.print("Introduzca el nuevo nombre >> ");
                String nombre = br.readLine();
                clase.setNombre(nombre);
                break;
            case 2:
                System.out.print("Introduzca el nuevo horario >> ");
                String horario = br.readLine();
                clase.setHorario(horario);
                break;
            case 3:
                System.out.print("Introduzca la nueva ubicación >> ");
                int aforo = Integer.parseInt(br.readLine());
                clase.setAforo(aforo);
                break;
            default:
                System.out.println("Opción incorrecta");
                return;
        }
        ClaseDAO.update(clase);
    }

    public static void deleteClase(SessionFactory sesion)throws IOException {
        System.out.print("Indique el ID de la clase a eliminar >> ");
        int id = Integer.parseInt(br.readLine());
        Clase clase = findClase(sesion,id);
        while(true) {
            if (clase != null) {
                ClaseDAO.delete(id);
                System.out.println("Clase borrada con éxito");
                break;
            } else {
                System.out.println("\nNo se encontro la clase");
                System.out.print("Introduzca un ID válido >> ");
                id = Integer.parseInt(br.readLine());
                clase = findClase(sesion,id);
            }
        }
    }

    public static void findAllClase(SessionFactory sesion) throws IOException {
        ClaseDAO cDAO = new ClaseDAO(sesion);
        List<Clase> claseList = cDAO.findAll();
        for (Clase clase : claseList) {
            System.out.println(clase.toString());
        }
    }    

    private static void printScreen(Terminal terminal, String message) throws InterruptedException {
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }
        System.out.println();
    }
}
