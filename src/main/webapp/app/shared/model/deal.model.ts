import { IClient } from '@/shared/model/client.model';
import { IGoods } from '@/shared/model/goods.model';

export interface IDeal {
  id?: number;
  date?: Date;
  amount?: string;
  client?: IClient;
  goods?: IGoods[];
}

export class Deal implements IDeal {
  constructor(public id?: number, public date?: Date, public amount?: string, public client?: IClient, public goods?: IGoods[]) {}
}
