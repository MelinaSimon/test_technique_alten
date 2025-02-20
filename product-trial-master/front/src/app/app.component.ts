import {
  Component,
  inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { LoginFormComponent } from "./auth/login/login.component";
import { DialogModule } from "primeng/dialog";
import { CommonModule } from "@angular/common";
import { AuthService } from "./auth/auth.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, LoginFormComponent, CommonModule, DialogModule],
})
export class AppComponent {
  title = "ALTEN SHOP";
  showLoginPopup = false;
  private readonly authService = inject(AuthService);

  toggleLoginPopup() {
    this.showLoginPopup = !this.showLoginPopup;
  }

  handleLogin(event: { email: string; password: string }) {
    console.log('Tentative de connexion avec les données:', event);

    this.authService.login(event.email, event.password).subscribe({
      next: (response) => {
        console.log('Token reçu:', response);
        localStorage.setItem('auth_token', response);
        this.toggleLoginPopup();
      },
      error: (error) => {
        console.error('Erreur lors de la connexion:', error);
      },
    });
  }
}
