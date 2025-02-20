import {
  Component,
  computed,
  EventEmitter,
  input,
  Output,
  ViewEncapsulation,
} from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { PasswordModule } from 'primeng/password';

@Component({
  selector: "app-login-form",
  template: `
    <form #form="ngForm" (ngSubmit)="onLogin()">
      <div class="form-field">
        <label for="email">Email</label>
        <input pInputText
          type="email"
          id="email"
          name="email"
          [(ngModel)]="loginData.email"
          required
          placeholder="Email"
        />
      </div>

      <div class="form-field">
        <label for="password">Mot de passe</label>
        <p-password 
          [(ngModel)]="loginData.password"
          name="password"
          id="password"
          required
          toggleMask
          placeholder="Mot de passe"
        ></p-password>
      </div>

      <div class="flex justify-content-between">
        <p-button type="button" (click)="onCancel()" label="Annuler" severity="help" />
        <p-button type="submit" [disabled]="!form.valid" label="Se connecter" severity="success" />
      </div>
    </form>
  `,
  styleUrls: ["./login.component.scss"],
  standalone: true,
  imports: [
    FormsModule,
    ButtonModule,
    InputTextModule,
    PasswordModule,
  ],
  encapsulation: ViewEncapsulation.None
})
export class LoginFormComponent {
  @Output() login = new EventEmitter<{ email: string; password: string }>();
  @Output() cancel = new EventEmitter<void>();

  loginData = {
    email: '',
    password: '',
  };

  onCancel() {
    this.cancel.emit();
  }

  onLogin() {
    this.login.emit(this.loginData);
  }
}
