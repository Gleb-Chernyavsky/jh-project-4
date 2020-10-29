<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhProject4App.deal.home.title')" id="deal-heading">Deals</span>
            <router-link :to="{name: 'DealCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-deal">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhProject4App.deal.home.createLabel')">
                    Create a new Deal
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && deals && deals.length === 0">
            <span v-text="$t('jhProject4App.deal.home.notFound')">No deals found</span>
        </div>
        <div class="table-responsive" v-if="deals && deals.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('date')"><span v-text="$t('jhProject4App.deal.date')">Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('jhProject4App.deal.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('client.name')"><span v-text="$t('jhProject4App.deal.client')">Client</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.name'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="deal in deals"
                    :key="deal.id">
                    <td>
                        <router-link :to="{name: 'DealView', params: {dealId: deal.id}}">{{deal.id}}</router-link>
                    </td>
                    <td>{{deal.date ? $d(Date.parse(deal.date), 'short') : ''}}</td>
                    <td>{{deal.amount}}</td>
                    <td>
                        <div v-if="deal.client">
                            <router-link :to="{name: 'ClientView', params: {clientId: deal.client.id}}">{{deal.client.name}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DealView', params: {dealId: deal.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DealEdit', params: {dealId: deal.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(deal)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhProject4App.deal.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-deal-heading" v-text="$t('jhProject4App.deal.delete.question', {'id': removeId})">Are you sure you want to delete this Deal?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-deal" v-text="$t('entity.action.delete')" v-on:click="removeDeal()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./deal.component.ts">
</script>
