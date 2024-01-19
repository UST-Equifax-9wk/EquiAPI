export class CartItem {
  constructor(
    public cartId: number,
    public productName: string,
    public productId: number,
    public price: number
  ) {}
}
