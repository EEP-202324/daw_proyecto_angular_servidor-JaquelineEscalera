import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversidadesComponent } from './universidades/universidades.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';
import { AgregarUniversidadComponent } from './agregar-universidad/agregar-universidad.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: UniversidadDetailComponent },
  { path: 'universidades', component: UniversidadesComponent },
  { path: 'agregar-universidad', component: AgregarUniversidadComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
