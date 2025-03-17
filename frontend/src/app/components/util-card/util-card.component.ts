import { Component, Input } from '@angular/core';

@Component({
  selector: 'wlt-util-card',
  imports: [],
  templateUrl: './util-card.component.html',
  styleUrl: './util-card.component.scss'
})
export class UtilCardComponent {

  @Input() title? : string;

}
