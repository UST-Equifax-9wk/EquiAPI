export class OrderDtos {
    constructor(public orderId: number,
                public dateOfPurchase: Date,
                public totalCost: number,
                public itemDescription: string []){}
}
