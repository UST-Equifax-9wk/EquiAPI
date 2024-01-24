export class OrderDtos {
  constructor(
    public orderId: number,
    public dateOfPurchase: string,
    public totalCost: number,
    public itemDescription: string[]
  ) {}
}
