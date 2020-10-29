import { ICategory } from '@/shared/model/category.model';
import { IDeal } from '@/shared/model/deal.model';

export interface IGoods {
  id?: number;
  name?: string;
  price?: number;
  photoContentType?: string;
  photo?: any;
  categories?: ICategory[];
  deals?: IDeal[];
}

export class Goods implements IGoods {
  constructor(
    public id?: number,
    public name?: string,
    public price?: number,
    public photoContentType?: string,
    public photo?: any,
    public categories?: ICategory[],
    public deals?: IDeal[]
  ) {}
}
