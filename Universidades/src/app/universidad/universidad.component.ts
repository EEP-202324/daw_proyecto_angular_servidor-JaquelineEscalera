import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import {UNIVERSIDAD} from '../mock-universidades';


@Component({
  selector: 'app-universidad',
  templateUrl: './universidad.component.html',
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  universidad : Universidad[] = UNIVERSIDAD;
  universidadSeleccionado?: Universidad;

  onSelect(universidad: Universidad): void {
    this.universidadSeleccionado = universidad;
  }
}
