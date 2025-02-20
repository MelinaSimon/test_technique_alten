import { Injectable, inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Cart } from "./cart.model";
import { environment } from "environments/environment";

@Injectable({
  providedIn: "root",
})
export class CartService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/api/cart`;

  constructor() {}

  addToCart(userId: number, productId: number, quantity: number): Observable<Cart> {
    return this.http.post<Cart>(`${this.baseUrl}/${userId}`, { productId, quantity });
  }
}
