import { Component, Input  } from '@angular/core';
import {Universidad} from '../universidad';

@Component({
  selector: 'app-universidad-detail',
  templateUrl: './universidad-detail.component.html',
  styleUrl: './universidad-detail.component.css'
})
export class UniversidadDetailComponent {
  @Input() universidad?: Universidad;
}
