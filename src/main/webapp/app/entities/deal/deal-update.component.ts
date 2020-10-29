import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import ClientService from '../client/client.service';
import { IClient } from '@/shared/model/client.model';

import GoodsService from '../goods/goods.service';
import { IGoods } from '@/shared/model/goods.model';

import AlertService from '@/shared/alert/alert.service';
import { IDeal, Deal } from '@/shared/model/deal.model';
import DealService from './deal.service';

const validations: any = {
  deal: {
    date: {},
    amount: {},
  },
};

@Component({
  validations,
})
export default class DealUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('dealService') private dealService: () => DealService;
  public deal: IDeal = new Deal();

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('goodsService') private goodsService: () => GoodsService;

  public goods: IGoods[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dealId) {
        vm.retrieveDeal(to.params.dealId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
    this.deal.goods = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.deal.id) {
      this.dealService()
        .update(this.deal)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhProject4App.deal.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.dealService()
        .create(this.deal)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhProject4App.deal.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.deal[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.deal[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.deal[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.deal[field] = null;
    }
  }

  public retrieveDeal(dealId): void {
    this.dealService()
      .find(dealId)
      .then(res => {
        res.date = new Date(res.date);
        this.deal = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.goodsService()
      .retrieve()
      .then(res => {
        this.goods = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
