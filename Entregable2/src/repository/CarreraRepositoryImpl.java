package repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import dto.EstudiantesPorCarreraDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;
import factory.ConnectionFactory;

public class CarreraRepositoryImpl implements CarreraRepository{
	EntityManager entityManager;

	public CarreraRepositoryImpl() {
		this.entityManager = ConnectionFactory.connection();
	}

	public CarreraRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Carrera insertarCarrera(Carrera carrera) {		
		if (this.obtenerCarreraPorNombre(carrera.getNombre()) == null) {
			entityManager.getTransaction().begin();	
			entityManager.persist(carrera);
			entityManager.getTransaction().commit();
		} 		
		return this.obtenerCarreraPorNombre(carrera.getNombre());		
	}	
	
	@Override
	public Carrera obtenerCarreraPorNombre(String nombre) {	
		TypedQuery<Carrera> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_NOMBRE, Carrera.class).setParameter("nombre", nombre);
		if (q.getResultList().isEmpty()) {
			return null;
		}
		Carrera carrera = q.getSingleResult();
		return carrera;
	}
	
	// f- Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
	@Override
	public List<Carrera> obtenerCarrerasOrdenadasporInscriptos() {
		TypedQuery<Carrera> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_INSCRIPTOS, Carrera.class);
		List<Carrera> carreras = q.getResultList();
		return carreras;
	}
	
	@Override
	public List<EstudiantesPorCarreraDTO> obtenerCarrerasOrdenadasporInscriptosDTO() {
		entityManager.getTransaction().begin();		
		TypedQuery<EstudiantesPorCarreraDTO> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_INSCRIPTOS_DTO, EstudiantesPorCarreraDTO.class);
		List<EstudiantesPorCarreraDTO> carreras = q.getResultList();		
		entityManager.getTransaction().commit();
		return carreras;
	}

	@Override
	public List<ReporteCarreraDTO> generarReporteCarrera() {
		String sql = "select c.nombre, "
				+ "       COALESCE(year, 0) year, "
				+ "       (SUM(CASE WHEN(EXTRACT(year from i.fecha_ingreso) = year.year) THEN 1 ELSE 0 END)) as inscriptos, "
				+ "       (SUM(CASE WHEN(i.esGraduado = true and EXTRACT(year from i.fecha_egreso) = year.year) THEN 1 ELSE 0 END)) as egresados "
				+ "from Carrera c "
				+ "    left join Inscripcion i on c.idCarrera = i.idCarrera "
				+ "    left join ( "
				+ "        select idCarrera, extract(year from fecha_egreso) year from Inscripcion where fecha_egreso is not null "
				+ "            union "
				+ "        select idCarrera, extract(year from fecha_ingreso) year from Inscripcion "
				+ "    ) year ON year.idCarrera = c.idCarrera "
				+ "GROUP BY c.nombre, i.idCarrera, year.year "
				+ "ORDER BY c.nombre, year.year";

		Query query = entityManager.createNativeQuery(sql);
		List<ReporteCarreraDTO> carreras = new ArrayList<ReporteCarreraDTO>();
        List<Object[]> results = query.getResultList();

        results.forEach(row ->
        	carreras.add(
    			new ReporteCarreraDTO(
	        			(String)row[0], 
	        			(BigDecimal)row[2], 
	        			(BigDecimal)row[3], 
	        			(BigInteger)row[1]
	    			)
				)
    		);
        return carreras;
	}
}
