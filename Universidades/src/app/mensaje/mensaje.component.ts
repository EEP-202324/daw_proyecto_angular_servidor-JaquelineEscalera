import { Component } from '@angular/core';
import { MensajeService } from '../mensaje.service';

@Component({
  selector: 'app-mensajes',
  templateUrl: './mensaje.component.html',
  styleUrl: './mensaje.component.css'
})
export class MensajesComponent {
  constructor(public servicioDeMensajes: MensajeService) {}
}
