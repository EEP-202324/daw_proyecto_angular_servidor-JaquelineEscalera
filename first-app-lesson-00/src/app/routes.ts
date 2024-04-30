import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { DetailsComponent } from "./details/details.component";

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Página de inicio'
  }, {
    path: 'details/:id',
    component: DetailsComponent,
    title: 'Detalle de la casa'
  }
];

export default routeConfig;
