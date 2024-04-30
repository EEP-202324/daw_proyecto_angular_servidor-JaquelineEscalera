print("programa de evaluacion de notas de alumnos")
nota_alumno=int(input())
def evaluacion(nota):
	valoracion="aprobado"
	if nota<5:
		valoracion="suspenso"
		return(valoracion)
print(evaluacion(nota_alumno))
