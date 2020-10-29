<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhProject4App.deal.home.createOrEditLabel" v-text="$t('jhProject4App.deal.home.createOrEditLabel')">Create or edit a Deal</h2>
                <div>
                    <div class="form-group" v-if="deal.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="deal.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.deal.date')" for="deal-date">Date</label>
                        <div class="d-flex">
                            <input id="deal-date" type="datetime-local" class="form-control" name="date" :class="{'valid': !$v.deal.date.$invalid, 'invalid': $v.deal.date.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.deal.date.$model)"
                            @change="updateZonedDateTimeField('date', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.deal.amount')" for="deal-amount">Amount</label>
                        <input type="text" class="form-control" name="amount" id="deal-amount"
                            :class="{'valid': !$v.deal.amount.$invalid, 'invalid': $v.deal.amount.$invalid }" v-model="$v.deal.amount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.deal.client')" for="deal-client">Client</label>
                        <select class="form-control" id="deal-client" name="client" v-model="deal.client">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="deal.client && clientOption.id === deal.client.id ? deal.client : clientOption" v-for="clientOption in clients" :key="clientOption.id">{{clientOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label v-text="$t('jhProject4App.deal.goods')" for="deal-goods">Goods</label>
                        <select class="form-control" id="deal-goods" multiple name="goods" v-model="deal.goods">
                            <option v-bind:value="getSelected(deal.goods, goodsOption)" v-for="goodsOption in goods" :key="goodsOption.id">{{goodsOption.name}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.deal.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./deal-update.component.ts">
</script>
