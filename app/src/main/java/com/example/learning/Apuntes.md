
# Apuntes

### Problemas Encontrados
___
1. En este proyecto no he tenido que eliminar suspend para para Room
ya que esto no me permitía ejecutar correctamente la app.

2. Notificar a recyclerView que se han producido cambios es muy ineficiente
usando notifyDataSetChanged() y supone reconstruir todos los elementos, lo cual
en lista grandes puede suponer una carga para la UI.

3. Al insertar los posters en la base de datos cuando se crea el viewModel
hace que se dupliquen los datos.


### Soluciones
___
1. La solución en este caso fue cambiar las versiones de Room ya
que estaba utilizando la version __1.7.0__ de kotlin y para era necesario
trabajar con la versión __2.5.0-alpha02__ de Room

2. Para ello introducimos el concepto de DiffUtil para actualizar
sólo las vistas que han cambiado

3. La solución pasa por eliminar el autogenerate en el Entity
porque con esta anotación hace que el replace del dao haga su trabajo
ya que siempre se generan pk nuevas por lo tanto cada vez que se inicia vuelva
a introducir los posters y se duplican. Para ello heredamos de la interfaz 
__LifecycleEventObserver__ la cual nos permite implementar un método con el que
le digamos a la interfaz que sólo inserte los datos cuando se crea el 
viewModel 

` 
override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    if(event == Lifecycle.Event.ON_CREATE) {
        viewModelScope.launch {
            FirstViewModel.posters.forEach {
                database.insertPoster(it)
            }
        }
    }
} 
`
