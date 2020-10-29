<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="deal">
                <h2 class="jh-entity-heading"><span v-text="$t('jhProject4App.deal.detail.title')">Deal</span> {{deal.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhProject4App.deal.date')">Date</span>
                    </dt>
                    <dd>
                        <span v-if="deal.date">{{$d(Date.parse(deal.date), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhProject4App.deal.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{deal.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhProject4App.deal.client')">Client</span>
                    </dt>
                    <dd>
                        <div v-if="deal.client">
                            <router-link :to="{name: 'ClientView', params: {clientId: deal.client.id}}">{{deal.client.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhProject4App.deal.goods')">Goods</span>
                    </dt>
                    <dd>
                        <span v-for="(goods, i) in deal.goods" :key="goods.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'GoodsView', params: {goodsId: goods.id}}">{{goods.name}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="deal.id" :to="{name: 'DealEdit', params: {dealId: deal.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./deal-details.component.ts">
</script>
