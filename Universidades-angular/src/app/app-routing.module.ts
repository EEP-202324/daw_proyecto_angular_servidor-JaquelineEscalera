import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversidadesComponent } from './universidades/universidades.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';

const routes: Routes = [
  { path: 'universidades', component: UniversidadesComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'detail/:id', component: UniversidadDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
